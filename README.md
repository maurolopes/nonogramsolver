# nonogramsolver

A solver for nonogram puzzles. It is written in Clojure and uses the core.logic library.

## Usage

All you need to do is call solve-nono with the specifications for its rows and columns.  
It returns the set of all solutions, each one represented as an array of rows.

Example:

lein repl  
Clojure 1.5.1  

user=> (use 'nonogramsolver.core)  
nil  
user=> (solve-nono [[3] [1 2] [1 1] [2 1]]  
  #_=>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; [[2 1] [1 2] [2] [3]])  
\#{[[1 1 1 0] [1 0 1 1] [0 1 0 1] [1 1 0 1]]}

Or better formatted:

1110  
1011  
0101  
1101  


## License

Copyright © 2014 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
