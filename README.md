## 介绍

个人回顾 IO 流所练习的项目

这项目其实就请求了 QQ 空间的那个接口，然后可以让你的网站（包括文章）发在 QQ 里会呈现出卡片信息。（有时候写完一篇水文之后，新发布的文章链接发在 QQ 里并不会出现卡片）

![images](https://user-images.githubusercontent.com/86390796/128879944-f4988ceb-41c2-45b3-be02-314bd5bc8e24.jpg)

注：

> 并不是所有的域名后缀都支持这个效果，目前好像只有 `.com`  `.cn`  `.cc`  `.net`  支持，其它的就不清楚了。
>
> PC QQ 是看不出效果的哈 😅

## 使用

贼简单！

1. fork 或者你直接克隆

2. 在 `Secrets` 中添加环境变量：

   |   Name   |                            Value                             |
   | :------: | :----------------------------------------------------------: |
   | FILE_URL | 远程地址，例如：`https://blog.imzjw.cn/baidu.txt` <br/>如果你是 Hexo 的话，可以使用 [Hexo-SEO-AutoPush](https://github.com/lete114/Hexo-SEO-AutoPush) 插件<br/>随后 hexo g 之后 Public 下就会有 baidu.txt 文件 |

3. Enable Actions

4. 修改仓库下的 `main.txt` 随便改点啥，加个空格都行，目的就是为了让 Actions 跑起来！

**触发机制：**

每隔两天的晚上九点运行，毕竟我写博客并不是很勤，有需求的自行更改
