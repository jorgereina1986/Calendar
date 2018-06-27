import { Events } from '.'

let events

beforeEach(async () => {
  events = await Events.create({ title: 'test', date: 'test', description: 'test', time: 'test' })
})

describe('view', () => {
  it('returns simple view', () => {
    const view = events.view()
    expect(typeof view).toBe('object')
    expect(view.id).toBe(events.id)
    expect(view.title).toBe(events.title)
    expect(view.date).toBe(events.date)
    expect(view.description).toBe(events.description)
    expect(view.time).toBe(events.time)
    expect(view.createdAt).toBeTruthy()
    expect(view.updatedAt).toBeTruthy()
  })

  it('returns full view', () => {
    const view = events.view(true)
    expect(typeof view).toBe('object')
    expect(view.id).toBe(events.id)
    expect(view.title).toBe(events.title)
    expect(view.date).toBe(events.date)
    expect(view.description).toBe(events.description)
    expect(view.time).toBe(events.time)
    expect(view.createdAt).toBeTruthy()
    expect(view.updatedAt).toBeTruthy()
  })
})
