# GitDownloader

This is a simple Java-based tool to download a directory, with all the
subdirectories and files, from any public github repository.

To use it, download the released jar file, and with the command

```
java -jar gitdownloader.jar <URL> <DIR>
```

URL is the [github API contents url](https://developer.github.com/v3/repos/contents/#get-contents). For example,
The GitHub API URL for the folder https://github.com/jiaola/gitdownloader/tree/master/src/main, would be
https://api.github.com/repos/jiaola/gitdownloader/contents/src/main?ref=master

DIR is the directory where you'd like to download the content into.


