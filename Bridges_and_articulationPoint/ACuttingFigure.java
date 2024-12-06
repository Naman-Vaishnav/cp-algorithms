//https://codeforces.com/problemset/problem/193/A
//package Bridges_and_articulationPoint;

import java.util.Scanner;



public class ACuttingFigure {

    static int n,m,ans=2;
    static boolean[][]  visited;
    static int[][]  tin;
    static int[][]  low;
    static int[][] dir={{1,0},{-1,0},{0,1},{0,-1}};
    static int timer=0;
    static void dfs(char[][] mat,int x,int y,int pX,int pY){
        visited[x][y]=true;
        tin[x][y]=low[x][y]=timer++;
        int children=0;
        for(int i=0;i<4;i++){
            int nX=x+dir[i][0],nY=y+dir[i][1];
            if(nX<n&&nX>=0&&nY<m&&nY>=0&&mat[nX][nY]=='#'){
                if(nX==pX&&nY==pY)continue;
                if(visited[nX][nY]==true){
                    low[x][y]=Math.min(low[x][y],tin[nX][nY]);
                }
                else{
                    dfs(mat,nX,nY,x,y);
                    low[x][y]=Math.min(low[x][y], low[nX][nY]);
                    if(low[nX][nY]>=tin[x][y]&&pX!=-1)ans=1;
                    children++;
                }
                
            }
        }
        
        if(children>1&&pX==-1)ans=1;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        n=sc.nextInt();m=sc.nextInt();
        sc.nextLine();/////////////////////////////////////////////<<<<<<<<
        char[][] mat=new char[n][m];
        low=new int[n][m];
        tin=new int[n][m];
        visited=new boolean[n][m];
        int area=0;
        for(int i=0;i<n;i++){
            String temp=sc.nextLine();
            for(int j=0;j<m;j++){
                mat[i][j]=temp.charAt(j);
                if(mat[i][j]=='#')area++;
            }
        }
        
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(mat[i][j]=='#'&&visited[i][j]==false){
                   dfs(mat,i,j,-1,-1);
                }
            }
        }
        if(area<=2)ans=-1;
        System.out.println(ans);

    }
}
