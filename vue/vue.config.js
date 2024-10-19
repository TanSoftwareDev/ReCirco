const {defineConfig} = require('@vue/cli-service')
module.exports = defineConfig({
    transpileDependencies: true,
    devServer: {
        port: 8088
    },
    chainWebpack: config => {
        config.plugin('html')
            .tap(args => {
                args[0].title = "悠换平台";
                return args;
            })
    }
})
