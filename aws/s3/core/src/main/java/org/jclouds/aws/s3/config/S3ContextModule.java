/**
 *
 * Copyright (C) 2009 Global Cloud Specialists, Inc. <info@globalcloudspecialists.com>
 *
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 */
package org.jclouds.aws.s3.config;

import org.jclouds.aws.s3.S3Connection;
import org.jclouds.aws.s3.S3Context;
import org.jclouds.aws.s3.commands.config.S3CommandsModule;
import org.jclouds.aws.s3.internal.GuiceS3Context;
import org.jclouds.aws.s3.internal.LiveS3InputStreamMap;
import org.jclouds.aws.s3.internal.LiveS3ObjectMap;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryProvider;

/**
 * Configures the {@link S3Context}; requires {@link S3Connection} bound.
 * 
 * @author Adrian Cole
 */
public class S3ContextModule extends AbstractModule {

   @Override
   protected void configure() {
      this.requireBinding(S3Connection.class);
      install(new S3CommandsModule());
      bind(GuiceS3Context.S3ObjectMapFactory.class).toProvider(
               FactoryProvider.newFactory(GuiceS3Context.S3ObjectMapFactory.class,
                        LiveS3ObjectMap.class));
      bind(GuiceS3Context.S3InputStreamMapFactory.class).toProvider(
               FactoryProvider.newFactory(GuiceS3Context.S3InputStreamMapFactory.class,
                        LiveS3InputStreamMap.class));
      bind(S3Context.class).to(GuiceS3Context.class);
   }

}
