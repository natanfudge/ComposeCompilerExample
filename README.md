# Compose From Zero


This repository demonstrates, as simply as possible, how to:
- Depend on the Compose runtime libraries and compiler plugin in any JVM environment.
- Go from @Composable methods to a tree-like structure (`Node`s).
- Print out a console GUI using the generated `Node`s.
- React to state changes using the Compose framework. 

To test the code, simply run the `main`. 
At first, a string will be printed, visually representing the tree: 
```
{<root>}: [
 {foo}
]
```
You may input a string yourself, and press enter, and a new tree will be generated, with the supplied string appended to the tree.
```
>>> regr
{<root>}: [
 {foo}: [
  {regr}
 ]
]
```
To stop the demo, enter `STOP`. 