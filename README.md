# GitDownloader

This is a simple Java-based tool to download a directory, with all the
subdirectories and files, from any public github repository.

Note: It requires to run with Java 8.

To use it, download the released jar file, and with the command

```
java -jar gitdownloader.jar <URL> <DIR>
```

URL is the [github API contents url](https://developer.github.com/v3/repos/contents/#get-contents). For example,
The GitHub API URL for the folder https://github.com/jiaola/gitdownloader/tree/master/src/main, would be
https://api.github.com/repos/jiaola/gitdownloader/contents/src/main?ref=master

DIR is the directory where you'd like to download the content into.

> Note: GitHub API has [rate limit rules](https://developer.github.com/v3/#rate-limiting) and you can only make 5,000
> requests per hour. Because GitDownloader recursively request each subdirectory and file in a directory, and each
> subdirectory and file cost a request, you may exceed this rate limit easily, and see an error message.