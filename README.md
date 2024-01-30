# Project Name
> Top-n email domains

## Table of Contents
* [Technologies Used](#technologies-used)
* [How to build](#how-to-build)
* [Documentation](#documentation )
* [Complexity Analysis](#complexity-analysis)
* [Room for Improvement](#room-for-improvement)

## Technologies Used
- JDK17
- maven 3.6+
- jUnit5 - for tests
- assert4J - for tests

## How to build
```shell
mvn clean package surefire-report:report
```

## Documentation
#### [EmailsParser.java](src%2Fmain%2Fjava%2Forg%2Fexample%2Fservice%2FEmailsParser.java)
> This class is used to extract domain from email address


#### [DomainCounter.java](src%2Fmain%2Fjava%2Forg%2Fexample%2Fservice%2FDomainCounter.java)
> This class is used to create domain statistics. It iterates over the given emails 
and extract the domain addresses using  #EmailsParser.java and counts all domains by caching it HashMap

#### [DomainRanker.java](src%2Fmain%2Fjava%2Forg%2Fexample%2Fservice%2FDomainRanker.java)
> This interface is used to rank and return top n values from domain statistics that done by DomainCounter.java

#### [HeapDomainRanker.java](src%2Fmain%2Fjava%2Forg%2Fexample%2Fservice%2FHeapDomainRanker.java)
> This is specific implementation of DomainRanker that calculates top n values from given statistics using 
PriorityQueue which is based on heap tree.  

#### [DomainsService.java](src%2Fmain%2Fjava%2Forg%2Fexample%2Fservice%2FDomainsService.java)
> This is a service class that combines other classes together and do the required job. 
This uses DomainCounter.java and DomainRanker.java dependencies to calculate statistics from given email list, 
rank and return top n values

## Complexity Analysis
### EmailsParser
> The time complexity of extracting the domain from an email address is O(m), where m is the length of the email address.

### DomainCounter
> Assuming there are 'k' email addresses and the average length of a domain is 'm', the time complexity of counting domains is O(k * m). This is because, for each email address, we perform O(m) operations to update the counts in the map.

### HeapDomainRanker
> Let 'd' be the number of unique domains. Finding the top 'n' domains takes O(n * log(d)) time using the heap.

## Room for Improvement
> As an improvement I'd implement one more ranker service which efficient on small collections and add a feature to choose 
different implementation based on given collection size and n (rank). Here strategy patten could be used to archive this goal.