# Google apps script mail merge

See [https://blog.laurentcharignon.com/post/mail-merge-in-100-lines-of-clojure/](https://blog.laurentcharignon.com/post/mail-merge-in-100-lines-of-clojure/) for more information.

## How to build

```shell
lein cljsbuild once main
cat export/Code.gs | pbcopy
# paste the code in google apps script
```

