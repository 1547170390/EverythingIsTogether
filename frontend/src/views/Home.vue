<template>
  <div class="home">
    <el-row :gutter="20">
      <el-col :span="24">
        <el-card class="welcome-card">
          <div class="welcome-content">
            <h2>欢迎使用 Everything Is Together</h2>
            <p>这是一个基于 Spring Boot 3 + Vue 3 的全栈项目</p>
            <el-button type="primary" size="large" @click="goToUsers">
              开始使用
            </el-button>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <el-row :gutter="20" class="feature-row">
      <el-col :span="8">
        <el-card class="feature-card">
          <el-icon size="40" color="#409EFF">
            <Back />
          </el-icon>
          <h3>Spring Boot 3</h3>
          <p>基于 Java 17 和 Spring Boot 3 的强大后端框架</p>
        </el-card>
      </el-col>
      
      <el-col :span="8">
        <el-card class="feature-card">
          <el-icon size="40" color="#67C23A">
            <View />
          </el-icon>
          <h3>Vue 3</h3>
          <p>现代化的前端框架，使用 Composition API 和 TypeScript</p>
        </el-card>
      </el-col>
      
      <el-col :span="8">
        <el-card class="feature-card">
          <el-icon size="40" color="#E6A23C">
            <Connection />
          </el-icon>
          <h3>前后端分离</h3>
          <p>RESTful API 设计，支持跨域请求</p>
        </el-card>
      </el-col>
    </el-row>

    <el-row :gutter="20" class="tech-row">
      <el-col :span="24">
        <el-card class="box-card project-info" shadow="hover">
          <template #header>
            <div class="card-header">
              <span>项目技术栈</span>
            </div>
          </template>
          <el-descriptions :column="1" border>
            <el-descriptions-item label="核心框架">Spring Boot 3</el-descriptions-item>
            <el-descriptions-item label="前端框架">Vue 3 + Vite</el-descriptions-item>
            <el-descriptions-item label="UI 库">Element Plus</el-descriptions-item>
            <el-descriptions-item label="数据库">MySQL</el-descriptions-item>
            <el-descriptions-item label="持久层">Spring Data JPA + Hibernate</el-descriptions-item>
            <el-descriptions-item label="版本控制">Git</el-descriptions-item>
          </el-descriptions>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router'
import { Back, View, Connection } from '@element-plus/icons-vue'

const router = useRouter()

const goToUsers = () => {
  router.push('/users')
}
</script>

<style scoped>
.home {
  max-width: 1200px;
  margin: 0 auto;
}

.welcome-card {
  margin-bottom: 30px;
  text-align: center;
}

.welcome-content h2 {
  color: #303133;
  margin-bottom: 15px;
}

.welcome-content p {
  color: #606266;
  font-size: 16px;
  margin-bottom: 20px;
}

.feature-row {
  margin-bottom: 30px;
}

.feature-card {
  text-align: center;
  height: 200px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.feature-card h3 {
  color: #303133;
  margin: 15px 0 10px 0;
}

.feature-card p {
  color: #606266;
  line-height: 1.6;
}

.tech-row {
  margin-bottom: 30px;
}
</style>
