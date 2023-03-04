# HypernymyFinder
**"Hypernymy"**, also called "IS-A relation", is a relation between two noun phrases, X and Y, meaning that: "X is a kind of Y". In this example, X is the *hypernym* of Y, and Y is the *hyponym* of X.

**Note 1:** this program works with a corpus that has annotated noun-phrases. The annotation for each noun-phrase is "<np> noun-phrase </np>". 
Such a curpus is present in this repository in the "/corpus" file - which contains 10 samples of files with Wikipidea articles. There is only 336MB worth of articles in this repository, meant to demonstrate the functionality of this program, while the original code was used with 1.3GB of annotated articles.

**Note 2:** this README assumes that apache-ant and JVM software is used for compiling and running the program. apache-ant should be version 1.10.12 or higher (might work just as well with older versions but I can't gurantee it).

**Compiling the program** - assuming you have apache-ant installed, compile with the terminal command: 
`ant compile`
in the "HypernymyFinder" directory. This command uses the build.xml file for compiling the code files.

This program has two functionalities: 
### **First Functionality** - Identifying Hypernymy in a given a corpus with annotated noun phrases
This functionality gets as input two parameters: **1. path to a corpus folder**. **2. path to an output folder**.
It then reads all of the files in the corpus folder, and identifies Hypernymy relations using *REGEX* of typical sentences that identify Hypernymy.
such as: (NP is initials for "noun-phrase")
"NP, (such as|including|especially) NP, NP, ..., NP (and|or) NP."
"such NP as NP, NP, ..., NP (and|or) NP."
"NP, which is (an example |a kind |a class ) of NP."

It then creates a new file with all the Hypernymy relations that it has found written in it in the format:
"hyper1: hypo1(2), hypo2(1)"
in which hyper1 is the hypernym, and hypo1 and hypo2 are it's hyponyms. hypo1 was found to be the hyponym of hyper1 in two seperate examples, while hypo2 was found to be the hyponym of hyper1 on one example.

this functionality can be run using the terminal command:
`ant run1 -Dargs="<corpus folder path> <output file folder path>"` 
**Note:** the path can be absolute or relative
for example: `ant run1 -Dargs="C:\Users\daniel\Desktop\HypernimyFinder\corpus C:\Users\daniel\Desktop\HypernimyFinder"`
