#Instructions

## Quick Start

1. Verifique se o JDK atual está instalado (nada mais é necessário - outras dependências serão baixadas automaticamente)

2. Para executar testes com o Chrome, execute: 
```
./gradlew
```

3. Para ajustar os testes com o PhatomJS (Headless), execute: 
```
./gradlew testPhantomJs
```

## Notes

Ao executar a partir de Gradle - o script de compilação tentará baixar automaticamente os drivers específicos do SO necessários para Chrome ou PhatomJS e atualizar as variáveis de ambiente de requisitos - phantomjs.binary.path ou webdriver.chrome.driver

Ao executar a partir de um IDE - você precisará configurar essas variáveis de ambiente e apontar para o driver da web correto

Exemplo para executar testes individuais a partir da linha de comando: gradle testPhantomJs -Dtest.single = TestNgExample

OBS: Versão do Webdrive utilizada é a "chromeDriverVersion = 2.24" com compatibilidade com a versão 53 do google chrome

Versão do Chrome utilizada: chrome64_52.0.2743.116.deb
```
Comando para remover versão atualizada para instalar versão antiga compativel
sudo apt-get remove google-chrome-stable
```

