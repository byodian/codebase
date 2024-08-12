package main

import (
  "fmt"
  "rsc.io/quote"
  "example.com/greetings"
)

func main() {
  fmt.Println(quote.Go())
  fmt.Println(greetings.Hello("World"))
}
