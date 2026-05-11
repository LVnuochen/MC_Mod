Add-Type -AssemblyName System.Drawing
$img = [System.Drawing.Image]::FromFile("D:\MC\1.20.1-Architectury-example\common\src\main\resources\assets\ak47-mod\textures\item\ak47.png")
Write-Host "Width: $($img.Width)"
Write-Host "Height: $($img.Height)"
Write-Host "PixelFormat: $($img.PixelFormat)"
$img.Dispose()
