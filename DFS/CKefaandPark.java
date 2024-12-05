//https://codeforces.com/problemset/problem/580/C
package DFS;

import java.util.*;

public class CKefaandPark {

    static int ans=0;
    public static void dfs(int cur,List<List<Integer>>adj,int m,boolean[] visited,int curCats,int[] isCat){
        visited[cur]=true;
        if(isCat[cur]==1)curCats++;
        else curCats=0;
        if(curCats>m)return;
        if(adj.get(cur).size()==1&&visited[adj.get(cur).get(0)]==true){
            ans++;
        }

        for(int child:adj.get(cur)){
            if(visited[child]==false){
                dfs(child,adj,m,visited,curCats,isCat);
            }
        }

    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt(),m=sc.nextInt();
        int[] isCat=new int[n+1];
        for(int i=1;i<=n;i++){
            isCat[i]=sc.nextInt();
        }

        List<List<Integer>> adj=new ArrayList<>();
        for(int i=1;i<=n+1;i++){
            adj.add(new ArrayList<>());
        }

        for(int i=1;i<=n-1;i++){
            int x=sc.nextInt(),y=sc.nextInt();
            adj.get(x).add(y);
            adj.get(y).add(x);
        }

        Integer curCats=0;
        boolean[] visited=new boolean[n+1];
        dfs(1,adj,m,visited,curCats,isCat);
        System.out.println(ans);

    }
}
