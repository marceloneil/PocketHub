/*
 * Copyright 2012 GitHub Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.pockethub.ui.user;

import com.alorma.github.sdk.bean.dto.response.GithubEvent;
import com.alorma.github.sdk.services.client.GithubClient;
import com.alorma.github.sdk.services.user.events.GetUserCreatedEventsClient;
import com.github.pockethub.core.ResourcePager;

import com.github.pockethub.core.PageIterator;

import org.eclipse.egit.github.core.Commit;

import java.util.List;

/**
 * News that a given user has created
 */
public class UserCreatedNewsFragment extends UserNewsFragment {

    @Override
    protected ResourcePager<GithubEvent> createPager() {
        return new EventPager() {

            @Override
            public PageIterator<GithubEvent> createIterator(int page, int size) {
                return new PageIterator<>(new PageIterator.GitHubRequest<List<GithubEvent>>() {
                    @Override
                    public GithubClient<List<GithubEvent>> execute(int page) {
                        return new GetUserCreatedEventsClient(getActivity(), org.login, page);
                    }
                }, page);
            }
        };
    }
}
