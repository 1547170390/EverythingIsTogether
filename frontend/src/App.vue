<template>
  <div id="app">
    <el-container>
      <el-header>
        <div class="header-content">
          <h1>Everything Is Together</h1>
          <el-menu
            mode="horizontal"
            :router="true"
            :default-active="$route.path"
            class="nav-menu"
          >
            <el-menu-item index="/">首页</el-menu-item>
            <el-menu-item index="/users">用户管理</el-menu-item>
            <el-menu-item index="/about">关于</el-menu-item>
          </el-menu>
        </div>
      </el-header>
      
      <el-main>
        <router-view />
      </el-main>
      
      <el-footer>
        <p>&copy; 2024 Everything Is Together. All rights reserved.</p>
      </el-footer>
    </el-container>
  </div>
</template>

<script setup>
import { useRoute } from 'vue-router'

const route = useRoute()
</script>

<style scoped>
#app {
  font-family: 'Helvetica Neue', Helvetica, 'PingFang SC', 'Hiragino Sans GB', 'Microsoft YaHei', '微软雅黑', Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

.el-header {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  color: white;
  padding: 0;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}

.header-content {
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 100%;
  padding: 0 20px;
}

.header-content h1 {
  margin: 0;
  font-size: 24px;
  font-weight: 600;
}

.nav-menu {
  background: transparent;
  border: none;
}

.nav-menu .el-menu-item {
  color: white;
  font-size: 16px;
}

.nav-menu .el-menu-item:hover {
  background-color: rgba(255, 255, 255, 0.1);
}

.nav-menu .el-menu-item.is-active {
  background-color: rgba(255, 255, 255, 0.2);
  color: white;
}

.el-main {
  min-height: calc(100vh - 120px);
  padding: 20px;
  background-color: #f5f7fa;
}

.el-footer {
  text-align: center;
  color: #666;
  padding: 20px;
  background-color: #fff;
  border-top: 1px solid #e4e7ed;
}
</style>
