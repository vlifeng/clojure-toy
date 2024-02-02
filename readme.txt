这是一个最基础的shadow-cljs 后台工程，包含最基础的包依赖项，一些默认的 db tools, web 服务器依赖等。

1. 首先你需要安装 node 14+ 环境，或者最新的nodejs 和npm 环境
2. npm shadow-cljs server 就可以启动repl 环境
3. 通过cider 或者 vscode 的 cider 插件打开 src 里任何一个clj 文件，就可以cider-connect-clj 连接 repl 了
4. 可以通过 run.sh 运行入口函数

一些指令：

npx shadow-cljs restart
npx shadow-cljs start
