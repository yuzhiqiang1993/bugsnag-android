# 环境搭建

首先拉取项目后，需要先执行,拉取需要的代码

```kotlin
git submodule update-- init --recursive 
```

git submodule update --init --recursive 的核心是读取 .gitmodules
文件中的配置，根据主仓库中记录的子模块版本信息，初始化子模块并同步到指定的版本。

如果想删除已经拉取过的子模块：
执行:

1. 删除子模块的本地内容
   git submodule deinit -f -- <submodule-path>

例如：

```Kotlin
git submodule deinit - f-- bugsnag -android - core / dsl - json
git submodule deinit - f-- bugsnag -plugin - android - ndk / src / main / jni / external / libunwindstack - ndk
```

2. 删除子模块的缓存
   rm -rf .git/modules/<submodule-path>
   例如：

```Kotlin
rm - rf.git / modules / bugsnag - android - core / dsl - json
rm - rf.git / modules / bugsnag - plugin - android - ndk / src / main / jni / external / libunwindstack - ndk
```

3. 删除子模块的工作目录
   直接删除子模块的文件夹：rm -rf <submodule-path>

```Kotlin
rm - rf bugsnag -android - core / dsl - json
rm - rf bugsnag -plugin - android - ndk / src / main / jni / external / libunwindstack - ndk 
```

4. 重新初始化并拉取子模块

```Kotlin
git submodule update-- init --recursive 
```

至此，代码准备完毕。