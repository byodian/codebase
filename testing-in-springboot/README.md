# Testing in SpringBoot

在 Spring Boot 中，测试框架是基于 JUnit 和 Spring 的核心测试支持，并提供了一些扩展工具来简化测试。主要的 Spring Boot 测试框架包括：

## JUnit 5 (Jupiter)
Spring Boot 默认使用 JUnit 5 作为测试框架，特别是 Spring Boot 2.2 及之后的版本。
JUnit 是一个轻量级测试框架，提供基本的测试功能如断言、异常处理和参数化测试。

常用注解：
- `@Test`: 标记测试方法。
- `@BeforeEach`, `@AfterEach`: 在每个测试之前或之后执行操作。
- `@BeforeAll`, `@AfterAll`: 在所有测试之前或之后执行一次操作。

## Spring Test (Spring Framework 提供的测试支持)

Spring 提供了对集成测试的支持，通过注解和配置简化测试应用上下文、依赖注入等。

常用注解：
- `@SpringBootTest`: 用于加载完整的 Spring 应用上下文。
- `@WebMvcTest`: 用于测试 Spring MVC 控制器，加载最小化的 Spring 上下文。
- `@DataJpaTest`: 用于测试 JPA 相关的持久层代码。
- `@MockBean`: 创建和注入 Mock 对象，以便测试依赖的类。

## MockMvc
MockMvc 是一个用于测试 Spring MVC 控制器的工具，允许在不启动整个服务器的情况下测试 HTTP 请求。
常见用法：
```java
@Autowired
private MockMvc mockMvc;

@Test
public void testGetEndpoint() throws Exception {
mockMvc.perform(get("/api/resource"))
    .andExpect(status().isOk())
    .andExpect(content().string(containsString("Hello World")));
}
```

## TestRestTemplate
TestRestTemplate 是一个用于集成测试的轻量级 HTTP 客户端，允许测试远程的 RESTful 服务。
常用于 Spring Boot 集成测试，特别是在 `@SpringBootTest` 上下文中。
常见用法：
```
@Autowired
private TestRestTemplate restTemplate;

@Test
public void testRestApi() {
    String body = this.restTemplate.getForObject("/api/resource", String.class);
    assertThat(body).contains("Hello World");
}
```

## Mockito
Mockito 是一个流行的 Mock 框架，用于模拟对象行为并验证交互。
在 Spring Boot 测试中可以通过 `@MockBean` 注解轻松集成 Mockito。

## AssertJ
AssertJ 是一个用于断言的流畅 API，提供更丰富的断言能力和可读性。
示例：
```
assertThat(actual).isEqualTo(expected);
assertThat(list).containsExactly("item1", "item2");
```
## 其他工具
- Spring Cloud Contract: 用于契约测试，特别是微服务之间的 API 调用。
- Testcontainers: 使用 Docker 容器在测试中模拟依赖服务（如数据库、消息队列）。
这些测试工具可以组合使用，提供单元测试、集成测试和功能测试的强大支持。在 Spring Boot 项目中，合理利用这些工具可以有效提升代码的可靠性和可维护性。
