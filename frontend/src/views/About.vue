<template>
  <div class="about">
    <el-card>
      <template #header>
        <span>关于项目</span>
      </template>
      
      <div class="about-content">
        <h2>Everything Is Together</h2>
        <p>这是一个现代化的全栈 Web 应用项目，展示了前后端分离架构的最佳实践。</p>
        
        <el-divider />
        
        <h3>项目特点</h3>
        <ul>
          <li>基于 Spring Boot 3 和 Java 17 构建</li>
          <li>使用 Vue 3 Composition API 开发前端</li>
          <li>RESTful API 设计</li>
          <li>响应式 UI 设计，支持移动端</li>
          <li>完整的 CRUD 操作示例</li>
          <li>跨域配置和错误处理</li>
        </ul>
        
        <el-divider />
        
        <h3>技术架构</h3>
        <el-row :gutter="20">
          <el-col :span="12">
            <h4>后端技术栈</h4>
            <ul>
              <li>Spring Boot 3.2.0</li>
              <li>Spring Data JPA</li>
              <li>MySQL</li>
              <li>Maven 构建工具</li>
              <li>Lombok</li>
            </ul>
          </el-col>
          <el-col :span="12">
            <h4>前端技术栈</h4>
            <ul>
              <li>Vue 3.3.8</li>
              <li>Vue Router 4</li>
              <li>Pinia 状态管理</li>
              <li>Element Plus UI 组件</li>
              <li>Vite 构建工具</li>
            </ul>
          </el-col>
        </el-row>
        
        <el-divider />
        
        <h3>开发环境要求</h3>
        <el-descriptions :column="1" border>
          <el-descriptions-item label="Java 版本">JDK 17 或更高版本</el-descriptions-item>
          <el-descriptions-item label="Node.js 版本">Node.js 18 或更高版本</el-descriptions-item>
          <el-descriptions-item label="Maven 版本">Maven 3.6 或更高版本</el-descriptions-item>
          <el-descriptions-item label="包管理器">npm 或 yarn</el-descriptions-item>
        </el-descriptions>
        
        <el-divider />
        
        <h3>快速开始</h3>
        <el-steps :active="4" finish-status="success" direction="vertical">
          <el-step title="启动后端服务" description="在 backend 目录下运行: mvn spring-boot:run" />
          <el-step title="安装前端依赖" description="在 frontend 目录下运行: npm install" />
          <el-step title="启动前端服务" description="在 frontend 目录下运行: npm run dev" />
          <el-step title="访问应用" description="前端: http://localhost:3000, 后端: http://localhost:8080" />
        </el-steps>
        
        <el-divider />
        
        <h3>项目结构</h3>
        <el-tree :data="projectStructure" :props="defaultProps" />
      </div>
    </el-card>
  </div>
</template>

<script setup>
import { ref } from 'vue'

const defaultProps = {
  children: 'children',
  label: 'label'
}

const projectStructure = ref([
  {
    label: 'EverythingIsTogether',
    children: [
      {
        label: 'backend',
        children: [
          { label: 'src/main/java/com/example' },
          { label: 'src/main/resources' },
          { label: 'pom.xml' }
        ]
      },
      {
        label: 'frontend',
        children: [
          { label: 'src/views' },
          { label: 'src/components' },
          { label: 'src/router' },
          { label: 'src/api' },
          { label: 'package.json' },
          { label: 'vite.config.js' }
        ]
      }
    ]
  }
])
</script>

<style scoped>
.about {
  max-width: 1000px;
  margin: 0 auto;
}

.about-content h2 {
  color: #303133;
  text-align: center;
  margin-bottom: 20px;
}

.about-content h3 {
  color: #409EFF;
  margin: 20px 0 15px 0;
}

.about-content h4 {
  color: #606266;
  margin: 15px 0 10px 0;
}

.about-content p {
  color: #606266;
  line-height: 1.6;
  margin-bottom: 15px;
}

.about-content ul {
  color: #606266;
  line-height: 1.8;
  margin-bottom: 15px;
}

.about-content li {
  margin-bottom: 5px;
}

.el-divider {
  margin: 30px 0;
}

.el-steps {
  margin: 20px 0;
}

.el-tree {
  margin: 20px 0;
}
</style>
