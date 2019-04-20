package structure.field

object Entry {

  def findEntries(text: String): Iterable[String] = text.split("@")

  def build(entries: Iterable[String]): String = entries.filterNot(_.isEmpty).mkString("@","\n@","")

  def main(args: Array[String]): Unit = {
    println(findEntries("@{victor,\nurl = {a}\n}\n@{victor,\nurl = {b}\n}\n").filterNot(_.isEmpty).mkString("@","\n@",""))
  }

}
