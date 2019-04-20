package main

import java.io.{FileReader, PrintWriter}

import com.typesafe.config.ConfigFactory
import structure.field.{Entry, Month, Url}
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Paths

object Main {

  def main(args: Array[String]): Unit = {
    val config = ConfigFactory.load()
    val inText = new String(Files.readAllBytes(Paths.get(config.getString("bibtex.in"))), StandardCharsets.UTF_8)
    val outFile = config.getString("bibtex.out")

    val res = Entry.build(Entry.findEntries(inText).map(Url.apply _ andThen Month.apply))

    Some(new PrintWriter(outFile)).foreach { p =>
      p.write(res)
      p.close()
    }

  }

}
