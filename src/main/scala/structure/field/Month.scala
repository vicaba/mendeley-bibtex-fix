package structure.field

case class Month(month: Int)

object Month extends Field {

  val January = "jan"
  val February = "feb"
  val March = "mar"
  val April = "apr"
  val May = "may"
  val June = "jun"
  val July = "jul"
  val August = "aug"
  val September = "sep"
  val October = "oct"
  val November = "nov"
  val December = "dec"

  val mapper: PartialFunction[String, Int] = {
    case January => 1
    case February => 2
    case March => 3
    case April => 4
    case May => 5
    case June => 6
    case July => 7
    case August => 8
    case September => 9
    case October => 10
    case November => 11
    case December => 12
  }

  override val fieldKey: String = "month"

  override def transformValue(str: String): String = fromBracketedString(str)

  def fromBracketedString(str: String): String = {
    if (mapper.isDefinedAt(str)) mapper(str).toString else str

  }

  def main(args: Array[String]): Unit = {
    val r = Month.fromBracketedString("{jun}")
    println(r)
  }

}
