ALang - A language
===================

A very tiny dynamically typed interpreted language.

#Features
~~~ python
#Alang is a dynamically typed language
    
#Just give a name and a value and you are
#ready to go.
hello := "world"
    
#Alang supports integers and strings
y := 42
    
#how to output things to console
println("Quick and easy")
    
# We have for loops with ranges
# 1 ~ 10 means from 1 to 10
for(i := 0 ~ 10) {
    println(i)
}
    
#Lists are made this way
x := [3, 9, 4, 6, 5, 1, 6, 7, 8, 10, 2]
    
#Lists are also dynamically typed
#so you can have lists like this
x := ["Hello", 10]
~~~

Getting started
---------------

Dependencies:

* Java **1.7** or newer

#### Source

To build `ALang` from source, you need to have [Apache Maven](https://maven.apache.org) installed.

~~~ sh
$ git clone https://github.com/nishtahir/ALang.git
$ cd ALang
$ mvn compile
~~~


LICENSE
=======

Copyright 2015 Nish Tahir

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
