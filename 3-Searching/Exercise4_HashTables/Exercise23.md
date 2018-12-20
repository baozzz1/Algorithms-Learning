###3.4.23
Since R is 256, and M is 255, everytime<br>
`hash = (R * hash + key.hashCode()) % M` is the same as <br>
`hash = (hash + key.hashCode()) % M`.<br>
So that, we get this formula,
[](https://github.com/baozzz1/Algorithms-Learning/master/3-Searching/Exercise4_HashTables/resources/picture1%20for%20Exercise23.png)
Whenever the string is ordered with any way, they all get the same hashcode.
