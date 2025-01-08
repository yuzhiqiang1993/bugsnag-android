cmake_minimum_required(VERSION 3.4.1)

# 添加库
add_library(
        native-lib
        SHARED
        native-lib.cpp
)

# 查找 log 库
find_library(
        log-lib
        log
)

# 链接库
target_link_libraries(
        native-lib
        ${log-lib}
)