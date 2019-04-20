package structure.field

import scala.util.matching.Regex

trait Field {

  val fieldKey: String

  lazy val fieldRegex: Regex = s"$fieldKey = \\{+(.+?)=?\\}[,|\\}]".r

  val valueRegex: Regex = "(?<=\\{).*(?=\\})".r

  def apply(entry: String): String = findFieldAndReplace(entry)

  def transformValue(str: String): String

  /**
    *
    * @param entry whole bibtex entry
    * @return
    */
  protected def findFieldAndReplace(entry: String): String = {
    findField(entry).map { _match =>
      val field = _match.group(0)
      val value = _match.group(1)
      val newValue = valueRegex.replaceFirstIn(field, transformValue(value))
      fieldRegex.replaceFirstIn(entry, newValue)
    }.getOrElse(entry)

  }

  protected def findField(string: String): Option[Regex.Match] = fieldRegex.findFirstMatchIn(string)

}

object Test {

  def main(args: Array[String]): Unit = {

    println(Url("@article{Jayadev2013,\nauthor = {Jayadev, V. and Swarup, K.S.},\ndoi = {10.1049/ic.2013.0124},\nfile = {:C$\\backslash$:/Users/vcaballero/AppData/Local/Mendeley Ltd./Mendeley Desktop/Downloaded/Jayadev, Swarup - 2013 - Optimization of microgrid with demand side management using Genetic Algorithm.pdf:pdf},\nisbn = {978-1-84919-792-2},\nkeywords = {by,demand side management,distributed generation,genetic algorithm,in grid connected mode,microgrid,mode,optimization,the excess power generated},\npages = {1--6},\ntitle = {{Optimization of microgrid with demand side management using Genetic Algorithm}},\nurl = {http://ieeexplore.ieee.org/ielx7/6712970/6718582/06718595.pdf?tp=&arnumber=6718595{\\&}isnumber=6718582{\\%}5Cnhttp://ieeexplore.ieee.org/xpl/articleDetails.jsp?arnumber=6718595{\\&}queryText=economic scheduling of distributed generators{\\&}newsearch=true},\nyear = {2013}\n}"))

  }

}
