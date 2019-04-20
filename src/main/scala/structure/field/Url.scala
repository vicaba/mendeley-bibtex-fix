package structure.field

import helper.Regex

object Url extends Field {

  override val fieldKey: String = "url"

  override def transformValue(str: String): String = (replace _ andThen takeOneUrl)(str)


  private def replace(str: String): String = Regex.replaceBracketedEscape(str).replace("%5Cn", " ")

  private def takeOneUrl(str: String): String = {
    val matches = "https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,4}\\b([-a-zA-Z0-9@:%_\\+.~#?&//={]*)".r.findAllIn(str).toList
    val ieeeMatches = matches.filter(_.contains("ieee"))
    if (ieeeMatches.nonEmpty) ieeeMatches.head
    else matches.headOption.getOrElse(str)
  }

  def main(args: Array[String]): Unit = {
    println(Url.apply("@article{Jayadev2013,\nabstract = {In a smart grid environment, economic operation means not only to economic scheduling of generation, but also to scheduling the load. In a Microgrid (MG), which comprises of intermittent Distributed Generators (DGs) (eg. solar and wind energy sources), the need of Demand Side Management (DSM)/ Demand Response (DR) becomes significant. The key point in DSM is to shift the load to some other point in time, this causes inconvenience to the customer and therefore it should be minimized. Minimizing the cost of generation and also minimizing the inconvenience caused due to shifting of loads is a multiobjective optimization problem. In this work the authors consider an industrial/ commercial MG with one solar source, two diesel generators and one battery, with the assumption that the utility grid uses dynamic pricing. The objective function contains discontinuous functions which will be difficult to solve using conventional optimization techniques and hence a Genetic Algorithm (GA) based solution is proposed. The simulation results show that there is savings for the customer with DSM compared to the case without DSM.},\nauthor = {Jayadev, V. and Swarup, K.S.},\ndoi = {10.1049/ic.2013.0124},\nfile = {:Users/vicaba/Library/Application Support/Mendeley Desktop/Downloaded/Jayadev, Swarup - 2013 - Optimization of microgrid with demand side management using Genetic Algorithm.pdf:pdf},\nisbn = {978-1-84919-792-2},\nkeywords = {by,demand side management,distributed generation,genetic algorithm,in grid connected mode,microgrid,mode,optimization,the excess power generated},\npages = {1--6},\ntitle = {{Optimization of microgrid with demand side management using Genetic Algorithm}},\nurl = {http://ieeexplore.ieee.org/ielx7/6712970/6718582/06718595.pdf?tp={\\&}arnumber=6718595{\\&}isnumber=6718582{\\%}5Cnhttp://ieeexplore.ieee.org/xpl/articleDetails.jsp?arnumber=6718595{\\&}queryText=economic scheduling of distributed generators{\\&}newsearch=true},\nyear = {2013}\n}"))
  }

}
