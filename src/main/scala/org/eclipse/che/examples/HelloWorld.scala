/*---------------------------------------------------------------------------------------------
 *  Copyright (c) Red Hat, Inc. All rights reserved.
 *  Licensed under the MIT License. See LICENSE in the project root for license information.
 *--------------------------------------------------------------------------------------------*/
package org.eclipse.che.examples

import cats.effect._
import cats.implicits._

object HelloWorld extends IOApp {

  override def run(args: List[String]): IO[ExitCode] = {
    val a = "Che"
    Console.io.putStrLn(s"Hello World $a!") *> IO.pure(ExitCode.Success)
  }

}
