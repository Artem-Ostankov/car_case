package models

import sorm._

/**
  * Created by arieel on 26.10.16.
  */
object DB extends Instance(entities = Seq(Entity[Car]()), url = "jdbc:h2:mem:test" , initMode = InitMode.DropAllCreate)
