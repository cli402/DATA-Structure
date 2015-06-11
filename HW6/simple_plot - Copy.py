"""
Provided code for Application portion of Module 1

Imports physics citation graph
"""

# general imports
import urllib2
import matplotlib

# Set timeout for CodeSkulptor if necessary
#import codeskulptor
#codeskulptor.set_timeout(20)


###################################
# Code for loading citation graph

CITATION_URL = "http://storage.googleapis.com/codeskulptor-alg/alg_phys-cite.txt"

def load_graph(graph_url):
    """
    Function that loads a graph given the URL
    for a text representation of the graph

    Returns a dictionary that models a graph
    """
    graph_file = urllib2.urlopen(graph_url)
    graph_text = graph_file.read()
    graph_lines = graph_text.split('\n')
    graph_lines = graph_lines[ : -1]

    print "Loaded graph with", len(graph_lines), "nodes"

    answer_graph = {}
    for line in graph_lines:
        neighbors = line.split(' ')
        node = int(neighbors[0])
        answer_graph[node] = set([])
        for neighbor in neighbors[1 : -1]:
            answer_graph[node].add(int(neighbor))

    return answer_graph

citation_graph = load_graph(CITATION_URL)


def compute_in_degrees(digraph):
    '''compute the in degree of the specific node'''
    digrap_degree={}
    for key in digraph:
        digrap_degree[key]=0
    for key in digraph:
        for element in digraph[key]:
            if element in digrap_degree:
                digrap_degree[element]+=1
    return digrap_degree

def in_degree_distribution(digraph):
    ''' Takes a directed graph digraph (represented as a dictionary)
        and computes the unnormalized distribution of
        the in-degrees of the graph'''
    distri_graph=compute_in_degrees(digraph)
    distri_num={}
    for key in distri_graph:
        element=distri_graph[key]
        if element in distri_num:
            distri_num[element]+=1
        else:
            distri_num[element]= 1
    return distri_num


def normalize_distribution(digraph):
    ''' Takes a directed graph digraph (represented as a dictionary)
        and computes the unnormalized distribution of
        the in-degrees of the graph'''
    distri_num=in_degree_distribution(digraph)
    total=0
    for key in distri_num:
        total+=distri_num[key]
    for key in distri_num:
        distri_num[key]=float(distri_num[key])/total
    return distri_num


b=normalize_distribution(citation_graph)
print b
import math
x=[]
y=[]
for key in b:
    x.append(key)
    y.append(b[key])

import matplotlib.pyplot as plt
plt.plot(x,y)
plt.loglog(x,y,'rp',basex=10,basey=10)
plt.xlim([0, 10**5])
plt.xlabel('In Degree ')
plt.ylabel('percentage')
plt.title('in-degree distribution of the citation graph')
plt.grid(True)
plt.savefig("test.png")
plt.show()

