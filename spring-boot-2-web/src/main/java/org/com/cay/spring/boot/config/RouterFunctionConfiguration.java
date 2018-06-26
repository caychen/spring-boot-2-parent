package org.com.cay.spring.boot.config;

/**
 * Created by Cay on 2018/5/9.
 */

import org.com.cay.spring.boot.entity.User;
import org.com.cay.spring.boot.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;

/**
 * 路由器函数配置
 */

@Configuration
public class RouterFunctionConfiguration {

	/**
	 * Servlet
	 *  请求接口：ServletRequest或者HttpServletRequest
	 *  响应接口：ServletResponse或者HttpServletResponse
	 *
	 * Spring5.0+重新定义了服务请求和响应接口
	 *  请求接口：ServerRequest
	 *  响应接口：ServerResponse
	 *
	 *  即可以支持Servlet规范，也可以支持自定义，比如Netty（Web Server）
	 *
	 * 以本例：
	 *  定义get请求，并且返回所有的用户对象，URL:/user/all
	 *
	 * Flux是0-N个对象集合
	 * Mono是0-1个对象集合
	 * Reactive中的Flux或者Mono是异步处理（非阻塞）
	 * 集合对象基本上是同步处理（阻塞）
	 *
	 * Flux或者Mono都是Publisher,
	 *
	 */
	@Bean
	public RouterFunction<ServerResponse> userAll(@Autowired IUserRepository userRepository){
		//返回所有的用户对象
		Collection<User> users = userRepository.all();

		//配置路由映射
		return RouterFunctions.route(RequestPredicates.GET("/user/all"),
				request -> {//HandlerFunction，用于处理请求
			Mono<ServerResponse> reponse = null;

			//将集合转换为Flux对象
			Flux<User> userFlux = Flux.fromIterable(users);
			return ServerResponse.ok().body(userFlux, User.class);
		});
	}
}
