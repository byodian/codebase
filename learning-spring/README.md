## Spring
- `@RestController` is short for including `@Controller` and `@ResponseBody`
- `@RequestMapping`
- `@GetMapping`
- `@PostMapping`
- `@PathVariable`
- `@RequestParam`
- `@SpringBootApplication`is a convenience annotation that adds all of the following:
- `@Configuration`
- `@EnableAutoConfiguration`
- `@ComponentScan`- Tells Spring to look for other componets, configurations, and services in the com/example package, letting it find the controllers.

The Greeting object must be converted to JSON. Thanks to Spring's HTTP message convert support, you need not do this conversion manually. Because Jackson2 is on the classpath, Spring's `MappingJackson2HttpMessageConverter` is automatically chosen to convert the Greeting instance to JSON.

The `main()` method uses Spring Boot's `SpringApplication.run()` method to launch an application. This web application is 100% pure Java and you did not have to deal with configuring any plumbing or infrastructure.

## [Record](https://docs.oracle.com/en/java/javase/14/language/records.html)

JDK 14 introduces records, which are a new kind of type declaration. Like an enum, a record is a restricted form of a class. It's ideal for "plain data carriers". classes that contain data not meant to be altered and only the most fundamental methods such as constructors and accessors.

## Jackson
This simple Java record class is annotated with `@JsonIgnoreProperties` from the Jackson JSON processing library to indicate that any properties not bound in this type should be ignored.

## References
- [Accessing JPA Data with REST](https://spring.io/guides/gs/accessing-data-rest/)
- [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
- [Accessing Data with MongoDB](https://spring.io/guides/gs/accessing-data-mongodb/)
- [Accessing Data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)

