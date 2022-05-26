package Deikstra;

import java.util.PriorityQueue;

public class DijkstraMain {

    public static void main(String[] args) {

        Vertex vertexA = new Vertex("A");
        Vertex vertexB = new Vertex("B");
        Vertex vertexC = new Vertex("C");
        Vertex vertexD = new Vertex("D");
        Vertex vertexE = new Vertex("E");
        Vertex vertexF = new Vertex("F");
        Vertex vertexG = new Vertex("G");

        vertexA.addNeighbour(new Edge(-0.757,vertexA,vertexB));
        vertexA.addNeighbour(new Edge(-0.027,vertexA,vertexG));
        vertexB.addNeighbour(new Edge(-0.757,vertexB,vertexA));
        vertexB.addNeighbour(new Edge(-0.051,vertexB,vertexG));
        vertexB.addNeighbour(new Edge(-0.466,vertexB,vertexE));
        vertexB.addNeighbour(new Edge(-1,vertexB,vertexC));
        vertexC.addNeighbour(new Edge(-0.804,vertexC,vertexD));
        vertexC.addNeighbour(new Edge(-1,vertexC,vertexB));
        vertexC.addNeighbour(new Edge(-0.432,vertexC,vertexE));
        vertexD.addNeighbour(new Edge(-0.804,vertexD,vertexC));
        vertexD.addNeighbour(new Edge(-0.456,vertexD,vertexE));
        vertexE.addNeighbour(new Edge(-0.432,vertexE,vertexC));
        vertexE.addNeighbour(new Edge(-0.466,vertexE,vertexB));
        vertexE.addNeighbour(new Edge(-0.456,vertexE,vertexD));
        vertexE.addNeighbour(new Edge(-0.319,vertexE,vertexF));
        vertexF.addNeighbour(new Edge(-0.319,vertexF,vertexE));
        vertexF.addNeighbour(new Edge(-0.509,vertexF,vertexG));
        vertexG.addNeighbour(new Edge(-0.027,vertexG,vertexA));
        vertexG.addNeighbour(new Edge(-0.051,vertexG,vertexB));
        vertexG.addNeighbour(new Edge(-0.509,vertexG,vertexF));

        DijkstraShortestPath shortestPath = new DijkstraShortestPath();
        shortestPath.computeShortestPaths(vertexA);

        System.out.println("Считаем минимальную дистанцию...: ");

        System.out.println("Минимальная дистанция A в B: "+vertexB.getDistance());
        System.out.println("Минимальная дистанция A в C: "+vertexC.getDistance());
        System.out.println("Минимальная дистанция A в D: "+vertexD.getDistance());
        System.out.println("Минимальная дистанция A в E: "+vertexE.getDistance());
        System.out.println("Минимальная дистанция A в F: "+vertexF.getDistance());
        System.out.println("Минимальная дистанция A в G: "+vertexG.getDistance());

        System.out.println("Строим пути...");

        System.out.println("Короткий пути из A в B: "+shortestPath.getShortestPathTo(vertexB));
        System.out.println("Короткий путь из A в C: "+shortestPath.getShortestPathTo(vertexC));
        System.out.println("Короткий путь из A в D: "+shortestPath.getShortestPathTo(vertexD));
        System.out.println("Короткий путь из A в E: "+shortestPath.getShortestPathTo(vertexE));
        System.out.println("Короткий путь из A в F: "+shortestPath.getShortestPathTo(vertexF));
        System.out.println("Короткий путь из A в G: "+shortestPath.getShortestPathTo(vertexG));


    }
    public void computeShortestPaths(Vertex sourceVertex){

        sourceVertex.setDistance(0);
        PriorityQueue<Vertex> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(sourceVertex);
        sourceVertex.setVisited(true);

        while( !priorityQueue.isEmpty() ){

            Vertex actualVertex = priorityQueue.poll();

            for(Edge edge : actualVertex.getAdjacenciesList()){

                Vertex v = edge.getTargetVertex();
                if(!v.isVisited())
                {
                    double newDistance = actualVertex.getDistance() + edge.getWeight();

                    if( newDistance < v.getDistance() ){
                        priorityQueue.remove(v);
                        v.setDistance(newDistance);
                        v.setPredecessor(actualVertex);
                        priorityQueue.add(v);
                    }
                }
            }
            actualVertex.setVisited(true);
        }
    }
}
