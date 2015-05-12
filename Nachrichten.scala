object Nachrichten {
  import org.htmlcleaner.HtmlCleaner
  import java.net.URL

  val host = "http://www.dw.de"
  val homepage = host + "/deutsch-lernen/nachrichten/s-8030"
  val cleaner = new HtmlCleaner

  def main(args: Array[String]) {
    println {
      if (args.length == 1) {
        val destination = args(0)
        download match {
          case Some(news) =>
            if (save(news, destination)) "News downloaded"
            else "Could not save the news"
          case None => "Could not retrieve the news"
        }
      } else "Output file name required as sole argument"
    }
  }

  def download = {
    getLatestUrl flatMap { getNews _ }
  }

  def getLatestUrl = {
    cleaner.clean(new URL(homepage)).getElementsByName("a", true) find {
      "news" == _.getParent.getAttributeByName("class")
    } map { host + _.getAttributeByName("href") }
  }

  def getNews(url: String) = {
    cleaner.clean(new URL(url)).getElementsByAttValue("class", "longText", true, true).headOption map {
      _.getChildTags map { _.getText.toString } reduce { _ + _ }
    }
  }

  def save(news: String, filename: String) = {
    import java.io.{ File, PrintWriter }

    val out = new File(filename)

    !out.exists() && {
      val writer = new PrintWriter(out)
      writer.write(news)
      writer.close()

      !writer.checkError()
    }
  }
}
