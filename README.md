# HypernymyFinder
"Hypernymy", also called "IS-A relation", is a relation between two noun phrases, X and Y, meaning that: "X is a kind of Y". In this example, X is the hypernym of Y, and Y is the hyponym of X.

**Note 1:** this program works with a corpus that has annotated noun-phrases. The annotation for each noun-phrase is "<np> noun-phrase </np>". 
Such a curpus is present in this repository in the "/corpus" file - which contains 10 samples of files with Wikipidea articles. There is only 336MB worth of articles in this repository, meant to demonstrate the functionality of this program, while the original code was used with 1.3GB of annotated articles.

**Note 2:** this README assumes that apache-ant software is used for compiling and running the program. version 1.10.12 or higher (might work just as well with older versions but I can't gurantee it).

This program has two functionalities: 
### **First Functionality** - Identifying Hypernymy in a given a corpus with annotated noun phrases
