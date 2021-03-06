package mesosphere.marathon
package integration

import akka.http.scaladsl.model.MediaTypes
import mesosphere.AkkaIntegrationTest
import mesosphere.marathon.integration.setup.EmbeddedMarathonTest

/**
  * Integration tests for non-app / non-pod end points such as /ping and /metrics
  */
class SystemResourceIntegrationTest extends AkkaIntegrationTest with EmbeddedMarathonTest {

  "Marathon" should {
    "respond to a ping" in {

      When("The system is pinged")
      val result = marathon.ping()

      Then("The system responses with a http 200 pong")
      result should be(OK)
      result.entityString should be("pong")

      And("The content type is text/plain")
      result.originalResponse.entity.contentType.mediaType should be(MediaTypes.`text/plain`)
    }
  }
}
