/*---------------------------------------------------------------------------------------------
 *  Copyright (c) Red Hat, Inc. All rights reserved.
 *  Licensed under the MIT License. See LICENSE in the project root for license information.
 *--------------------------------------------------------------------------------------------*/
package org.eclipse.che.examples

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

/**
  * Unit test for simple App.
  */
class HelloWorldTest extends AnyFlatSpec with Matchers {
  "HelloWorld.greet" should "say Hello _!" in {
    HelloWorld.greet("World").unsafeRunSync() should ===("Hello World!")
  }
}
