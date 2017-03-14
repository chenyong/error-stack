
Error Stack
----

Simple page to show error stack.

```
boot build-advanced
export deps=`boot show -c`
lumo -c $deps:src -i tasks/render.cljs
http-server -c-1
```

### Develop

Workflow https://github.com/mvc-works/stack-workflow

### License

MIT
