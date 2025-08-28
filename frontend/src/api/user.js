import axios from 'axios'

// 创建 axios 实例
const api = axios.create({
  baseURL: '/api',
  timeout: 10000,
  headers: {
    'Content-Type': 'application/json'
  }
})

// 请求拦截器
api.interceptors.request.use(
  (config) => {
    // 可以在这里添加认证 token
    return config
  },
  (error) => {
    return Promise.reject(error)
  }
)

// 响应拦截器
api.interceptors.response.use(
  (response) => {
    // 处理统一响应格式
    const { data } = response
    if (data && typeof data === 'object' && 'code' in data) {
      // 如果是统一响应格式
      if (data.code === 200) {
        // 成功响应，返回data字段的内容
        response.data = data.data
        return response
      } else {
        // 错误响应
        const error = new Error(data.message || '请求失败')
        error.response = response
        return Promise.reject(error)
      }
    }
    // 非统一响应格式，直接返回
    return response
  },
  (error) => {
    // 统一错误处理
    if (error.response) {
      console.error('API Error:', error.response.data)
    } else if (error.request) {
      console.error('Network Error:', error.request)
    } else {
      console.error('Error:', error.message)
    }
    return Promise.reject(error)
  }
)

// 用户 API
export const userApi = {
  // 获取所有用户
  getUsers() {
    return api.get('/users')
  },

  // 根据 ID 获取用户
  getUserById(id) {
    return api.get(`/users/${id}`)
  },

  // 创建用户
  createUser(userData) {
    return api.post('/users', userData)
  },

  // 更新用户
  updateUser(id, userData) {
    return api.put(`/users/${id}`, userData)
  },

  // 删除用户
  deleteUser(id) {
    return api.delete(`/users/${id}`)
  },

  // 健康检查
  health() {
    return api.get('/users/health')
  }
}

export default api
