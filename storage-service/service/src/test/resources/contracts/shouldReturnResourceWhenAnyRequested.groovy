package contracts

import org.springframework.cloud.contract.spec.Contract
Contract.make {
    description "should return resource where requested any"
    request{
        method GET()
        url("/resources/[a-zA-Z0-9]+") {
        }
    }
    response {
        file('src/test/resources/test_resource.mp3')
        status 200
    }
}