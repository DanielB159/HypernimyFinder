# HypernymyFinder
## Introduction
**"Hypernymy"**, also called "IS-A relation", is a relation between two noun phrases, X and Y, meaning that: "X is a kind of Y". In this example, X is the *hypernym* of Y, and Y is the *hyponym* of X.

**Note 1:** this program works with a corpus that has annotated noun-phrases. The annotation for each noun-phrase is "<np> noun-phrase </np>".  
Such a curpus is present in this repository in the "/corpus" file - which contains 10 samples of files with Wikipidea articles. There is only 336MB worth of articles in this repository, meant to demonstrate the functionality of this program, while the original code was used with 1.3GB of annotated articles.

**Note 2:** this README assumes that apache-ant and JVM software is used for compiling and running the program. apache-ant should be version 1.10.12 or higher (might work just as well with older versions but I can't gurantee it).

**Compiling the program** - assuming you have apache-ant installed, compile with the terminal command:  
`ant compile`
in the "HypernymyFinder" directory. This command uses the build.xml file for compiling the code files.  
**Note:** this repository already has the 'bin' folder that includes the compiled files. Compiling again will simply replace them with the newly compiled files.  

## Key Learnings
By developing this project, i have gained experience in the following areas:  
- Identifying *Hearst Patterns* in a corpus Using *REGEX*.
- *OOP* principles such as encapsulation and interfaces.
- Implementing the Singleton Design Pattern.
- Implementing the Template Design Pattern.  

## Usage and examples
This program has two functionalities:  
#### **First Functionality** - Identifying Hypernymy in a given a corpus with annotated noun phrases  
This functionality gets as input two parameters: **1. path to a corpus folder**. **2. path to an output folder**.  
It then reads all of the files in the corpus folder, and identifies Hypernymy relations using *REGEX* of typical sentences that identify Hypernymy.  
such as: (NP is initials for "noun-phrase")  
"NP, (such as|including|especially) NP, NP, ..., NP (and|or) NP."  
"such NP as NP, NP, ..., NP (and|or) NP."  
"NP, which is (an example |a kind |a class ) of NP."  
  
It then creates a new file with all the Hypernymy relations that it has found written in it called "hypernym_db.txt" in the format:  
"hyper1: hypo1(2), hypo2(1)"  
in which hyper1 is the hypernym, and hypo1 and hypo2 are it's hyponyms. hypo1 was found to be the hyponym of hyper1 in two seperate examples, while hypo2 was found to be the hyponym of hyper1 on one example.
**Note**: the program writes to the file only hypernyms with 3 found hyponyms of higher.

this functionality can be run using the terminal command:  
`ant run1 -Dargs="<corpus folder path> <output file folder path>"`  
**Note:** the path can be absolute or relative  
for example: `ant run1 -Dargs="C:\Users\daniel\Desktop\HypernimyFinder\corpus C:\Users\daniel\Desktop\HypernimyFinder"`  
  
  
#### **Second Functionality** - finding hypernyms to a given hyponym  
This functionality gets as input two parameters: **1. path to a corpus folder**. **2. a hyponym**.  
It then reads the corpus the same way it read it in the first functionality, and outputs all possible hypernyms to the given hyponym.

this functionality can be run using the terminal command:  
`ant run2 -Dargs="<corpus folder path> <hyponym>"`  
**Note:** here also, path can be absolute or relative  
for example: using the corpus example in this repository with the terminal command: `ant run1 -Dargs="C:\Users\daniel\Desktop\HypernimyFinder\corpus apple"`  
will yield the results:  
  
```
fruit: (3)
shrub: (2)
the rose family: (2)
agricultural symbol: (1)
another sweet fruit: (1)
every day: (1)
family plant: (1)
favorite online store: (1)
fertilize fruit tree: (1)
fruits: (1)
important relative: (1)
late fall: (1)
local ingredient: (1)
many susceptible plant: (1)
operating-system manufacturer: (1)
rotten produce: (1)
seeds: (1)
source: (1)
sweet ingredient: (1)
the harder wood: (1)
tree fruit: (1)
whole fruit: (1)
```
