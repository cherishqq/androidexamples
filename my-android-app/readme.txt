#####################
TODO
1: add service 
2: thread.
 需要增加对线程的支持

3: database
4: query
5: need to test many kinds of status


user manual
1: how to use database
 1: init database:
  config : AndroidManifest.xml
 
 1: add one table
   define one table --> DataStore.java 
   define path, add sUriMatcher -->DBContentProvider
   
 
 query data -->  DBContentProviderHelper
 
 DBContentProvider: support method (query,bulkInsert,insert,update,delete)
 
 自定义控件有个问题,就是在xml 里面,要连通包名一起改

