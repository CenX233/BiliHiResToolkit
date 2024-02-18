<span>
    <img style="width:56px;margin-left:32px;margin-top:16px" src="src/main/resources/icon.svg" alt="logo">
</span>
<span style="font-size:32px">
    BiliHiResToolKit
</span>

***

BiliHiResToolKit(下称“工具”)是专用于混流制作Bilibili视频网站Hi-Res规格视频的工具，可快捷将视频与无损音频重新封装到mkv或mp4格式中以便投稿。

## 功能

***

- 支持将任意格式的无损音频文件自动转码适合的格式。  
    Bilibili要求的音频格式为 48/96kHz 24/32bit，具体二压规则的文字描述为：
  - 支持格式：alac、ape、dts、flac、mp4als、ralf、shorten、mlp、tak、wavpack、wmalossless、pcm  
  - 采样率和位深必须大于或等于 48kHz、24bit
  - 若 48kHz ≤ 上传的稿件音频采样率 < 96kHz，则平台输出的无损音质为 48kHz 
  - 若 上传的稿件音频采样率 ≥ 96kHz，则平台输出的无损音质为 96kHz  
  完整描述可按以下路径找到：  
    - PC网页端创作中心右下角“遇到问题” > (往下翻)UP主服务 > 稿件创作及上传 > 直接向智能小助手发送文字“Hi-res无损音质投稿要求是什么？”  
    
  该工具将会进行例如以下的转换以避免平台二压：  
    <table>
        <tr>
            <td>44.1kHz 16bit wav</td>
            <td>--转码--></td>
            <td>48kHz 24bit flac</td>
        </tr>
        <tr>
            <td>48kHz 24bit flac</td>
            <td>--复制--></td>
            <td>48kHz 24bit flac</td>
        </tr>
        <tr>
            <td>48kHz 32bit(浮点) pcm</td>
            <td>--转码--></td>
            <td>48kHz 32bit(整型) flac</td>
        </tr>
        <tr>
            <td>88.2kHz 24bit flac</td>
            <td>--转码--></td>
            <td>48kHz 24bit flac</td>
        </tr>
        <tr>
            <td>96kHz 32bit pcm</td>
            <td>--复制--></td>
            <td>96kHz 32bit als(仅限mp4)</td>
        </tr>
    </table>
  虽然平台也会以类似规则进行转码，但此工具允许你可视化编辑ffmpeg的重采样器、响度控制(如果需要)的参数，可能能够获得比平台默认参数更好的效果(?
  
  另外此工具也会将44.1kHz、16bit(在一些老歌以及CD上十分常见)转换为48kHz、24bit以满足投稿要求。
- 如果需要，此工具也能使用简单的参数来重新编码视频。

## 支持的平台
此工具由纯Java编写，编解码后端为ffmpeg，理论上只要能够运行jre8或以上以及ffmpeg就可以使用此工具。  
如果你不知道这俩是什么，那么以下表格可供参考:
- ✔: 提供打包好的所有组件，可直接运行
- (✔):未经过验证或需要自行配置jdk/jre和ffmpeg
- -:不存在

<table style="text-align:center">
    <tr>
        <th>操作系统 / 架构</th>
        <th>x86</th>
        <th>ARM</th>
        <th>LoongArch</th>
    </tr>
    <tr>
        <th>Windows</th>
        <td>✔</td>
        <td>(✔)</td>
        <td>-</td>
    </tr>
    <tr>
        <th>MacOS</th>
        <td>(✔)</td>
        <td>(✔)</td>
        <td>-</td>
    </tr>
    <tr>
        <th>Linux</th>
        <td>✔</td>
        <td>(✔)</td>
        <td>(✔)</td>
    </tr>
</table>
提供的jar包从jdk17构建。