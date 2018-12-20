#3.4.23
Since R is 256, and M is 255, everytime 
`hash = (R * hash + key.hashCode()) % M` is the same as 
`hash = (hash + key.hashCode()) % M`.<br>
So that, we get this formula,
[]()
Whenever the string is ordered with any way, they all get the same hashcode.
