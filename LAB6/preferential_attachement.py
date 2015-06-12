
def make_complete_graph(num_nodes):
    '''return a complete graph if num_nodes is positive
        else return an empty graph'''
    if(num_nodes>0):
        completegraph={}
        for iume in range(0,num_nodes):
            completegraph[iume]=set([])
            for jexe in range(0,num_nodes):
                    if jexe!=iume:
                        completegraph[iume].add(jexe);
    return completegraph

"""
Provided code for application portion of module 1

Helper class for implementing efficient version
of DPA algorithm
"""

# general imports
import random

class DPATrial:
    """
    Simple class to encapsulate optimized trials for DPA algorithm

    Maintains a list of node numbers with multiple instances of each number.
    The number of instances of each node number are
    in the same proportion as the desired probabilities

    Uses random.choice() to select a node number from this list for each trial.
    """

    def __init__(self, num_nodes):
        """
        Initialize a DPATrial object corresponding to a
        complete graph with num_nodes nodes

        Note the initial list of node numbers has num_nodes copies of
        each node number
        """
        self._num_nodes = num_nodes
        self._node_numbers = [node for node in range(num_nodes) for dummy_idx in range(num_nodes)]


    def run_trial(self, num_nodes):
        """
        Conduct num_node trials using by applying random.choice()
        to the list of node numbers

        Updates the list of node numbers so that the number of instances of
        each node number is in the same ratio as the desired probabilities

        Returns:
        Set of nodes
        """

        # compute the neighbors for the newly-created node
        new_node_neighbors = set()
        for dummy_idx in range(num_nodes):
            new_node_neighbors.add(random.choice(self._node_numbers))

        # update the list of node numbers so that each node number
        # appears in the correct ratio
        self._node_numbers.append(self._num_nodes)
        self._node_numbers.extend(list(new_node_neighbors))

        #update the number of nodes
        self._num_nodes += 1
        return new_node_neighbors

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

def normalize_distribution(digraph):
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
    total=0
    total_edges=0
    for key in distri_num:
        total+=distri_num[key]
        total_edges+=key*distri_num[key]
    for key in distri_num:
        distri_num[key]=float(distri_num[key])/total
    print "the total \n"
    print total_edges
    print "the m is \n"
    print total_edges/total
    return distri_num

def preferential_attachment(total,piece):
    #the preferential attachement principle
    initial_graph=make_complete_graph(piece)
    inst=DPATrial(piece)
    for i in range(piece,total):
        retu_node=inst.run_trial(piece)
        initial_graph[i]=retu_node
    return initial_graph

a=preferential_attachment(27770,12)
b=normalize_distribution(a)

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

