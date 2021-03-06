/*
 * Copyright 2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.greglturnquist.hackingspringboot.reactive;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import com.greglturnquist.hackingspringboot.reactive.InventoryService;
import com.greglturnquist.hackingspringboot.reactive.Item;

/**
 * @author Greg Turnquist
 */
// tag::code[]
@RestController
class InventoryController {

	private InventoryService service;

	InventoryController(InventoryService service) {
		this.service = service;
	}

	@GetMapping(value = "/items", produces = "application/stream+json")
	Flux<Item> findInventoryData(@RequestParam("q") String q) {
		return this.service.getItems() //
				.filter(item -> item.getName().contains(q));
	}
}
// end::code[]
