package helper

object Regex {

  /**
    * Transforsm {\&} into &
    * @param str
    * @return
    */
  def replaceBracketedEscape(str: String): String = "\\{\\\\(.)\\}".r.replaceAllIn(str, "$1")

  def main(args: Array[String]): Unit = {
    println(replaceBracketedEscape("""http://ieeexplore.ieee.org/ielx7/6712970/6718582/06718595.pdf?tp=&arnumber=6718595{\&}isnumber=6718582{\%}5Cnhttp://ieeexplore.ieee.org/xpl/articleDetails.jsp?arnumber=6718595{\&}queryText=economic scheduling of distributed generators{\&}newsearch=true"""))
  }


}
