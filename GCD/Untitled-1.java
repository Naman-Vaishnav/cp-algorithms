

Reader fr=new Reader();
int n=fr.nextInt();
String needle=fr.nextLine();
char ch;
int[] LPS=new int[n+1];
LPS[0]=0;
for(int i=1;i<=n;i++){
    int j=LPS[i-1];
    while(j>0&&s[i]!=s[j])j=LPS[j-1];
    if(s[i]==s[j])j++;
    LPS[i]=j;
}
StringBuilde sb=new StringBuilder();
while(ch=fr.nextChar()!=null){
    int j=LPS[n];
    while(j>0&&s[j]!=ch)j=LPS[j-1];
    if(s[j]==ch)j++;
    sb.a
}
