# Day6

## Spring Framework

스프링 프레임워크란?

자바 플랫폼을 위한 오픈소스 애플리케이션 프레임워크로서 엔터프라이즈급 애플리케이션을 개발하기 위한 모든 기능을 종합적으로 제공하는 경량화된 솔루션입니다.

엔터프라이즈급 개발이란 뜻대로만 풀이하면 기업을 대상으로 하는 개발이라는 말입니다. 즉, 대규모 데이터 처리와 트랜잭션이 동시에 여러 사용자로 부터 행해지는 매우 큰 규모의 환경을 엔터프라이즈 환경이라 일컫습니다.

**Spirng Framework는 경량 컨테이너로 자바 객체를 담고 직접 관리**합니다. 객체의 생성 및 소멸 그리고 라이프 사이클을관리하며 언제든 Spring 컨테이너로 부터 필요한 객체를 가져와 사용할 수 있습니다. 

이는 Spirng이 IOC 기반의 Framework임을 의미합니다.

### Spring Framework설치

1. 아래의 링크에서 다운로드 후 설치.

https://spring.io/tools <br><br>

2. 설치 후 스프링 프레임워크를 실행하고, Help -> Eclipse Marketplace에서 legacy를 검색 후 다음과 같이 spring tools를 설치한다.

![KakaoTalk_20211006_213935820](https://user-images.githubusercontent.com/87796075/136245621-9e4ebf5d-97c3-42d7-8128-33d72fe9aaa9.png)



![KakaoTalk_20211006_214025963](https://user-images.githubusercontent.com/87796075/136233215-554a73d7-9c9c-4da9-a0d3-df8de9f46151.png)

다음과 같이 전부 선택 후 confirm을 누르고 동의를 해준 후 설치를 해준다.<br><br>

3. 설치 후 다음과 같이 spring legacy project를 하나 생성해준다.

![image-20211006224843728](https://user-images.githubusercontent.com/87796075/136233371-e8c0a25c-fca8-4214-b7fa-0f68f8c368b5.png)



![image-20211006224926673](https://user-images.githubusercontent.com/87796075/136233488-bfc8c24a-e87a-44f4-975e-5ac99ee9df4c.png)

위와 같이 spring mvc project를 선택후 next로 넘어간다.

![image-20211006224942728](https://user-images.githubusercontent.com/87796075/136233554-724629c1-065b-48e6-93fc-535a56cff6a6.png)

위와같이 패키지명을 양식에 맞게 작성해주고 finish를 눌러 프로젝트 생성을 마친다.<br><br>

4. 설치 후 스프링 프레임워크를 실행하고, Help -> Install New Software에서 다음과 같이 설정을 해주고 설치한다.

![KakaoTalk_20211006_214618438](https://user-images.githubusercontent.com/87796075/136233673-344694b4-e2b4-4928-8d80-a6e0f86a4d25.png)



![KakaoTalk_20211006_214640603](https://user-images.githubusercontent.com/87796075/136233750-487780f4-62d5-4b5d-b696-2a47504b9bca.png)

위와 같이 설정하고 next를 누른다.

![KakaoTalk_20211006_214702292](https://user-images.githubusercontent.com/87796075/136233817-577a484e-41ce-44f0-b440-f9e298343206.png)

다음과 같이 선택을 여러개 할 수 있는데, 여기서 Web으로 시작하는 해당 부분만 체크 후 설치한다.<br><br>

5. 설치가 완료되면 다음과 같이 open perspective에서 web, xml, java EE 등이 추가됨을 확인할 수 있다.

![image-20211007001046564](https://user-images.githubusercontent.com/87796075/136233959-c3c7591d-b65a-4b05-823a-be456961d8ca.png)
<br><br>

6. 모든 설치가 완료된 후, 다음과 같은 설정들을 preferences에서 차례대로 해주고 환경 설정을 마무리 한다.

![image-20211007001239941](https://user-images.githubusercontent.com/87796075/136234035-a9b77c3c-7d82-416a-90ee-da995ba017b2.png)

위와 같이 spelling에서 encoding을 utf-8로 바꿔주고 적용한다.

![image-20211007001248921](https://user-images.githubusercontent.com/87796075/136234123-28c4f85f-0066-4ce2-89c4-a3fa040d47e8.png)

web의 jsp files, html files, css files 각각 모두 encoding을 utf-8로 바꿔준다.

![image-20211007001257262](https://user-images.githubusercontent.com/87796075/136234199-4c17daf3-ae08-41ac-8dc5-74418bd39898.png)

마지막으로 server에서 runtime environment로 들어가서 add를 누르고 설치해놓은 tomcat을 추가한다.

![image-20211007001308254](https://user-images.githubusercontent.com/87796075/136234288-320a8516-ebf7-4f17-aa03-b2de0cce8911.png)


위와같이 tomcat이 있는 경로를 찾아서 맞춰주고 설정을 마친다.
