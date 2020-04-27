/*---------------------------------------------------------------------------------------------
 *  Copyright (c) Red Hat, Inc. All rights reserved.
 *  Licensed under the MIT License. See LICENSE in the project root for license information.
 *--------------------------------------------------------------------------------------------*/
package org.eclipse.che.examples

import cats.effect._

object HelloWorld extends IOApp {

  override def run(args: List[String]): IO[ExitCode] = {
    val che = "Che"
    for {
      greeting <- greet(che)
      _ <- Console.io.putStrLn(greeting)
    } yield ExitCode.Success
  }

  def greet(name: String): IO[String] = {
    IO.pure(s"Hello $name!")
  }

}
