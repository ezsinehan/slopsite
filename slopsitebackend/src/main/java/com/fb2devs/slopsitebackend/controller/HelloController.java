// this is a package declaration => defines the namespace for this class, helps organize related classes and avoid naming conflicts
package com.fb2devs.slopsitebackend.controller;

// importing specific classes from the spring framework libaries that are needed in this file
  // GetMapping is annotation to map http requests to specific handler methods
  // RestController is annotation that marks the class as a REST controller
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// this annotation combines @Controller and @ResponseBody
  // it tells Spring that this class will handle http requests and the return calues from methods should be bound to the web response body rather than view name
@RestController
public class HelloController {
  // maps HTTP GET requestions to the path /api/hello
  @GetMapping("/api/hello")
  public String hello() {
    return "backend is running";
  }
}
